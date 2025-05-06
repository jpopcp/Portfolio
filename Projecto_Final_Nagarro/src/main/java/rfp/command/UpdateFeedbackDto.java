package rfp.command;

public class UpdateFeedbackDto {
    private String positivePoints;
    private String negativePoints;
    private String description;
    private String finalMark;

    public String getPositivePoints() {
        return positivePoints;
    }

    public void setPositivePoints(String positivePoints) {
        this.positivePoints = positivePoints;
    }

    public String getNegativePoints() {
        return negativePoints;
    }

    public void setNegativePoints(String negativePoints) {
        this.negativePoints = negativePoints;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFinalMark() {
        return finalMark;
    }

    public void setFinalMark(String finalMark) {
        this.finalMark = finalMark;
    }
}