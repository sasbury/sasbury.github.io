
function runCode(code, logId) {
    try
    {
        eval(code);
    }
    catch(err)
    {
        $("#"+logId).append(err.message+'\n');
    }
}

function runGraphVis(code, svgId, logId) {
    try
    {
        svg = Viz(code, 'svg');
        $('#'+svgId).html(svg);
    }
    catch(err)
    {
        $("#"+logId).append(err.message+'\n');
    }
}

function readSharedCode() {
    var sharedCode = "";

    $('.shared').each(function(index,element){

      sharedCode += $(element).text();
      sharedCode += "\n";
    });

    return sharedCode;
}

function processRunnables() {
    $('.runnable').each(function(index,element){
        var code = $(element).text();
        var logId = "rLog-"+index;

        code = readSharedCode() +"function log(str){$('#"+logId+"').append(str+'\\n');}\n"+code;

        $('<pre id="'+logId+'"></pre>').insertAfter(element);

        runCode(code,logId);
    });
}

function processDrawables() {
    $('.drawable').each(function(index,element){
        var code = $(element).text();
        var width = $(element).data('canvas-width') || 400;
        var height = $(element).data('canvas-height') || 300;
        var logId = "dLog-"+index;

        code = readSharedCode() + "var canvas = document.getElementById('canvas-"+index+"');\n"+
                "var drawing = canvas.getContext('2d');\n"+
                "drawing.fillStyle = \"#000\";\n" +
                code;

        var newHtml = '<canvas id="canvas-'+index+'" width="'+width+'" height="'+height+'"></canvas>';

        if($(element).hasClass('loggable')) {
            code = "function log(str){$('#"+logId+"').append(str+'\\n');}\n"+code;
            newHtml = newHtml + '<pre id="'+logId+'"></pre>';
        }

        $(newHtml).insertAfter(element);

        runCode(code,logId);
    });

    $('.graphviz').each(function(index,element){
        var code = $(element).text();
        var logId = "vLog-"+index;
        var newHtml = '<div id="svg-'+index+'"></div>';

        if($(element).hasClass('loggable')) {
            newHtml = newHtml + '<pre id="'+logId+'"></pre>';
        }

        $(newHtml).insertAfter(element);

        runGraphVis(code, 'svg-'+index, logId);
    });
}

function processSpecials() {
    processRunnables();
    processDrawables();
    prettyPrint();
}
