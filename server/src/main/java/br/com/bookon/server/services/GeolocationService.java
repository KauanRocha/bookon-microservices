package br.com.bookon.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.bookon.server.payload.response.postgres.NominatimAdressResponse;
import br.com.bookon.server.payload.response.postgres.NominatimGeolocationResponse;

@Service
public class GeolocationService {
	
	private final String NOMINATIM_URL_REVERCE = "https://nominatim.openstreetmap.org/reverse";
	private final String NOMINATIM_URL_SEARCH = "https://nominatim.openstreetmap.org/search";

    @Autowired
    private RestTemplate restTemplate;

    public NominatimAdressResponse getCityStateCountry(double latitude, double longitude) {
        String url = NOMINATIM_URL_REVERCE + "?format=xml&lat=" + latitude + "&lon=" + longitude;
        System.out.println(url);
        ResponseEntity<NominatimAdressResponse> response = restTemplate.getForEntity(url, NominatimAdressResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
        	
            System.err.println("Erro na solicitação: Código de status " + response.getStatusCode());
            return null;
        }


    };
    
    public NominatimGeolocationResponse geocodeAddress(String address) {
        String url = NOMINATIM_URL_SEARCH + "?q=" + address + "&format=xml&polygon_kml=1&addressdetails";
        System.out.println(url);
        ResponseEntity<NominatimGeolocationResponse> response = restTemplate.getForEntity(url, NominatimGeolocationResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
        	
            System.err.println("Erro na solicitação: Código de status " + response.getStatusCode());
            return null;
        }


    }
    
}
