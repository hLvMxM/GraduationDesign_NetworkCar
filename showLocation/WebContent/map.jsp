<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>地图</title>
</head>
<body>
	<div id="container" style="height:600px;width:800px"></div>
	<script src="https://webapi.amap.com/maps?v=1.4.8&key=af5971b3a9f70a8bda9bed4a2536a682"></script>
	<script type="text/javascript" src = 'https://a.amap.com/jsapi_demos/static/citys.js'></script> 
	<script type="text/javascript">
	    var map = new AMap.Map('container', {
	    	center:[104.0762260000,30.6510760000],
            zoom:12
	    });
	    var la = 120.0;
	    var lo = 40.0;
	    var markers = [];
	    var markersobjects = [];
	    for(var i=0;i<3000;i++){
		    markers.push({
		        icon: 'http://localhost:8080/showLocation/poi-marker-default.png',
		        position: [la,lo]
		    })
		    la += 0.1;
		    lo += 0.1;
	    }
	    markers.forEach(function(marker) {
	        var tmp = new AMap.Marker({
	            map: map,
	            icon: marker.icon,
	            position: [marker.position[0], marker.position[1]],
	            offset: new AMap.Pixel(-13, -30)
	        });
	        markersobjects.push(tmp);
	    });
	</script>
</body>
</html>