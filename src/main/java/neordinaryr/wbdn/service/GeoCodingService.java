package neordinaryr.wbdn.service;

import org.springframework.beans.factory.annotation.Value;
import neordinaryr.wbdn.domain.dto.response.GeoCodingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoCodingService {
    @Value("${google.maps.api-key}")
    private String googleMapsApiKey;

    public String getAddress(double latitude, double longitude) {
        String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" +
                latitude + "," + longitude + "&key=" + googleMapsApiKey;

        RestTemplate restTemplate = new RestTemplate();
        GeoCodingResponse response = restTemplate.getForObject(apiUrl, GeoCodingResponse.class);

        // 주소 정보 추출 및 반환
        if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
            return response.getResults().get(0).getFormattedAddress();
        }

        return "주소를 찾을 수 없습니다.";
    }
}
