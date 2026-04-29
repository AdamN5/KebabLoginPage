package ie.atu.kebabeloginwebpage.client;

import ie.atu.kebabeloginwebpage.dto.KebabReviewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "review-service", url = "${review.service.url}")
public interface ReviewClient {

    @GetMapping("/api/reviews")
    List<KebabReviewDTO> getAll();

    @PostMapping("/api/reviews")
    KebabReviewDTO create(@RequestBody KebabReviewDTO review);
}