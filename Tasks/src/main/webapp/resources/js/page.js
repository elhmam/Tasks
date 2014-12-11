$(document).ready(function(){
    //attach a jQuery live event to the button
    $('#getdata-button').click(function(){
        $.getJSON('http://localhost:8080/projects', function(dataDS) {
        	var htmlString="";
        	htmlString="<p>Status="+dataDS.status+"</p>";
        	htmlString= htmlString+"<ul>";
            $(dataDS.data.projectsTasks).each(function(index){
            	htmlString= htmlString+'<li> Project : ' + dataDS.data.projectsTasks[index].project + '</li>';
            	htmlString= htmlString+"<ul>";
            	$(dataDS.data.projectsTasks[index].tasks).each(function(index1){
                 	htmlString= htmlString+'<li> Task : ' + dataDS.data.projectsTasks[index].tasks[index1].id + '</li>';
                 }); 
            	htmlString= htmlString+"</ul>";
            });
            htmlString= htmlString+"</ul>";
            $('#showdata').html(htmlString);           
        });
    });
});