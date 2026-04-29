package ie.atu.kebabeloginwebpage.dto;

public record KebabReviewDTO(Long id, String username, String restaurant, String comment, int rating) {
    public KebabReviewDTO() {
        this(null, null, null, null, 0);
    }
}