from __future__ import with_statement

import sys
import os.path
import shutil
import zipfile
import itertools
import yaml
import markdown
import PyRSS2Gen

from filecmp import dircmp
from datetime import datetime
from flask import Flask, render_template,abort,make_response
from flask_frozen import Freezer
from HTMLParser import HTMLParser

DEBUG = True
BUILD_FOLDER = '../build'
OLD_BUILD_FOLDER = '../build_old'
UPLOAD_FOLDER = '../upload'+datetime.strftime(datetime.utcnow(), "_%Y_%b_%d_%H_%M_%S")
FREEZER_DESTINATION = BUILD_FOLDER

app = Flask(__name__)
app.config.from_object(__name__)
freezer = Freezer(app)

file_cache = {}

#
# From http://stackoverflow.com/questions/753052/strip-html-from-strings-in-python
#
class MLStripper(HTMLParser):
    def __init__(self):
        self.reset()
        self.fed = []
    def handle_data(self, d):
        self.fed.append(d)
    def get_data(self):
        return ''.join(self.fed)

def strip_tags(html):
    s = MLStripper()
    s.feed(html)
    return s.get_data()

#
# Based on Flask FlatPages
#
def parsePage(string, path):

    lines = iter(string.split(u'\n'))
    extensions = ['codehilite']
    page = {}

    page['path'] = path
    page['meta_yaml'] = u'\n'.join(itertools.takewhile(unicode.strip, lines))
    page['content'] = u'\n'.join(lines)
    page['meta'] = yaml.safe_load(page['meta_yaml'])
    page['html'] = markdown.markdown(page['content'], extensions)
    page['summary'] = strip_tags(page['html'])[:280]+' ...' #first set of characters

    if page['meta']['tags'] is None:
        page['meta']['tags'] = []

    return page

def processFile(path, filepath):
    mtime = os.path.getmtime(filepath)
    with open(filepath) as fd:
        content = fd.read().decode('utf8')
    page = parsePage(content, path)
    page['mtime'] = mtime
    page['filepath'] = filepath
    return page

def processFolder(directory, path_prefix=(),pages={}):

    for name in os.listdir(directory):
        full_name = os.path.join(directory, name)
        if os.path.isdir(full_name):
            processFolder(full_name, path_prefix + (name,),pages)
        elif name.endswith('.md') and not name.startswith('_'):
            name_without_extension = name[:-len('.md')]
            new_name = name_without_extension+'.html'
            path = u'/'.join(path_prefix+(new_name,))
            pages[path] = processFile(path, full_name)
    return pages

def getPage(path,pages, default=None):
    page = None
    try:
        page = pages[path]
        filepath = page['filepath']
        mtime = os.path.getmtime(filepath)
        if(page['mtime']!=mtime):
            page = processFile(path,filepath)
    except KeyError:
        page = default

    return page

def getPageDate(page):

    dateString = str(page['meta']['date'])

    if dateString.startswith('circa'):
        return dateString.split(' ')[1]
    elif dateString == '' or page['meta']['date'] is None:
        return -1;
    elif dateString == 'archive':
        return "2012";#rank these above circa, as a string like circa will be
    else:
        return dateString.split(' ')[0]

pages = processFolder(os.path.join(app.root_path,u'pages'))

#
# figure out posts versus projects and set up back links
# 
projects = []
posts = []

for path,page in pages.items():
    if path.startswith('projects'):
        page['parent_url'] = '/portfolio/'
        page['parent'] = 'Back to portfolio'
        projects.append(page)
    elif path.startswith('posts'):
        page['parent_url'] = '/notes/'
        page['parent'] = 'Back to notes'
        posts.append(page)

#
#build rss feed from posts
#
rssItems = []

for page in posts:

    dateString = getPageDate(page)
    dt = None

    if isinstance(dateString, str) or isinstance(dateString, unicode):
        if len(dateString)==4:
            dt = datetime.strptime(dateString, "%Y")
        else:
            dt = datetime.strptime(dateString, "%Y-%m-%d")
 
    item = PyRSS2Gen.RSSItem(
         title = page['meta']['title'],
         link = 'https://www.sasbury.com'+'/'+page['path'],
         description = page['summary'],
         guid = PyRSS2Gen.Guid('/'+page['path']),
         pubDate = dt)

    rssItems.append(item)

rss = PyRSS2Gen.RSS2(
    title = "sasbury.com feed",
    link = "https://www.sasbury.com",
    description = "sasbury.com RSS feed",
    docs = '',
    lastBuildDate = datetime.utcnow(),
    items = rssItems
    )

rssFeed = rss.to_xml('utf-8')

#
#set up routes
#
@app.route('/')
def index():
    sorted_posts = sorted(posts, reverse=True, key=getPageDate)
    sorted_projects = sorted(projects, reverse=True, key=getPageDate )
    return render_template('index.html', posts=sorted_posts[:3],projects=sorted_projects[:3]
                            ,projCount=len(projects),postCount=len(posts))

@app.route('/notes/')
def notes():
    sorted_posts = sorted(posts, reverse=True, key=getPageDate)
    return render_template('notes.html', posts=sorted_posts)

@app.route('/notes/sasbury_rss.xml')
def rss():
    return rssFeed, 200, {'Content-Type': 'application/xml; charset=utf-8'}

@app.route('/portfolio/')
def portfolio():
    sorted_projects = sorted(projects, reverse=True, key=getPageDate )
    return render_template('portfolio.html', projects=sorted_projects)

@app.route('/tag/<string:tag>/')
def tag(tag):
    
    tagged = []

    for path,page in pages.items():
        tags = page['meta']['tags']
        if tag in tags:
            tagged.append(page)

    sorted_pages = sorted(tagged, reverse=True, key=getPageDate)
    return render_template('tag.html', pages=sorted_pages, tag=tag)

@app.route('/<path:path>')
def page(path):
    page = getPage(path,pages)

    if not page:
        abort(404)

    return render_template('page.html', page=page)

#
# include book sites for freeze
# 
@freezer.register_generator
def books_url_generator():
    # URLs as strings
    yield '/books/ejava.html'
    yield '/books/ejava2.html'
    yield '/books/jfc.html'
    yield '/books/lxatwork.html'

def processDiff(dcmp):

    #left is old build, right is new build
    
    #first cmp won't have any left only, but later cmps might
    if len(dcmp.left_only) > 0:

            src = dcmp.left
            print "Files or folders were deleted from %s, copying full folder" % (src)
            dest = src.replace(OLD_BUILD_FOLDER,UPLOAD_FOLDER)
            shutil.copytree(src,dest)
    else:
        to = dcmp.left.replace(OLD_BUILD_FOLDER,UPLOAD_FOLDER)

        if not os.path.exists(to) and len(dcmp.diff_files)>0:
            os.mkdir(to)

        #copy new files
        for name in dcmp.right_only:

            src = os.path.join(dcmp.right,name)
            dest = src.replace(BUILD_FOLDER,UPLOAD_FOLDER)

            if os.path.isdir(src):
                shutil.copytree(src,dest)
            else:
                parent = os.path.dirname(dest)

                if not os.path.exists(parent):
                    print "creating %s" % (parent)
                    os.makedirs(parent)

                shutil.copy(src,dest)

        #copy changed files
        for name in dcmp.diff_files:
            src = os.path.join(dcmp.right,name)
            dest = src.replace(BUILD_FOLDER,UPLOAD_FOLDER)
            shutil.copy(src,dest)

        #recurse
        for sub_dcmp in dcmp.subdirs.values():
            processDiff(sub_dcmp)

#
# Main app code
# 
if __name__ == '__main__':

    if len(sys.argv) > 1 and sys.argv[1] == "build":

        if os.path.exists(OLD_BUILD_FOLDER):
            print "Removing build backup"
            shutil.rmtree(OLD_BUILD_FOLDER)

        if os.path.exists(BUILD_FOLDER):
            print "Moving previous build to build backup"
            os.rename(BUILD_FOLDER,OLD_BUILD_FOLDER)
        else:
            print "Creating placeholder build backup"
            os.mkdir(OLD_BUILD_FOLDER)

        if not os.path.exists(BUILD_FOLDER):
            print "Creating build folder"
            os.mkdir(BUILD_FOLDER)

        print "Compiling and saving site"
        freezer.freeze()

        print "Create diff of build with previous build"
        dcmp = dircmp(OLD_BUILD_FOLDER, BUILD_FOLDER)

        ##check if we deleted anything, if so, we should copy the whole folder and be done
        if len(dcmp.left_only) > 0:
            print "Files or folders were deleted, copying full build"
            shutil.copytree(BUILD_FOLDER,UPLOAD_FOLDER)
        else:
            print "Creating temporary upload folder"
            os.mkdir(UPLOAD_FOLDER)
            processDiff(dcmp)

        print "Creating zip of upload folder"
        zip = zipfile.ZipFile(UPLOAD_FOLDER+".zip", 'w', zipfile.ZIP_DEFLATED)
        for base, dirs, files in os.walk(UPLOAD_FOLDER):
            for file in files:
                fn = os.path.join(base, file)
                zip.write(fn, fn[len('../'):])#keep the upload folder name but not the ../ part

        if os.path.exists(UPLOAD_FOLDER):
            print "Removing temporary upload folder"
            shutil.rmtree(UPLOAD_FOLDER)

        if os.path.exists(OLD_BUILD_FOLDER):
            print "Removing build backup"
            shutil.rmtree(OLD_BUILD_FOLDER)

    elif len(sys.argv) > 1 and sys.argv[1] == "static":

        freezer.run(port=8080)
    
    elif len(sys.argv) > 1 and sys.argv[1] == "public":
    
        app.run(host='0.0.0.0',port=8080)
    
    else:
    
        app.run(port=8080)
