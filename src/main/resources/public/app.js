$(document).ready(function () {
    var that = this;
    var map;

    function bootstrap() {
        mapboxgl.accessToken = 'pk.eyJ1IjoiZ2lzLWRlbW8iLCJhIjoiY2puaHRzMnFhMGhqbDNrbzVsZmlrZTJzYyJ9.IGKbpPcePdHuQg_1YKAMKg';
        map = new mapboxgl.Map({
            container: 'map',
            style: 'mapbox://styles/mapbox/streets-v10',
            center: [-97.724940, 30.385440],
            zoom: 13,
            doubleClickZoom: false
        });
        setupEvents();
    }

    function setupEvents() {
        map.on('dblclick', function(event) {
            loadData(event.lngLat);
        });
    }

    function loadData(lngLat) {
        if (map.getSource('current')) {
            map.getSource('current').setData(getDataUrl(lngLat));
        } else {
            map.addSource('current', {
                type: 'geojson',
                data: getDataUrl(lngLat)
            });
            map.addLayer({
                "id": "rides",
                "type": "symbol",
                "icon-allow-overlap": true,
                "source": "current",
                "layout": {
                    "icon-image": "rocket-15"
                }
            });
        }

        map.setCenter(lngLat);

    }

    function getDataUrl(lngLat) {
        return '/nearEnd?lat=' + lngLat.lat + '&lon=' + lngLat.lng + '&radius=1000';
    }

    bootstrap();
});