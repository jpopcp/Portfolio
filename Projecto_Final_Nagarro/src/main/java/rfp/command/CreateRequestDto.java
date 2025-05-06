package rfp.command;

import rfp.model.Request;
import java.time.LocalDate;
import java.util.Map;

public class CreateRequestDto {
    private Integer clientId;
    private LocalDate deliveryDate;
    private String description;
    private String specifications;
    private Request.Status status;
    private Request.Priority priority;
    private Map<String, Integer> evaluationCriteria;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public Request.Status getStatus() {
        return status;
    }

    public void setStatus(Request.Status status) {
        this.status = status;
    }

    public Request.Priority getPriority() {
        return priority;
    }

    public void setPriority(Request.Priority priority) {
        this.priority = priority;
    }

    public Map<String, Integer> getEvaluationCriteria() {
        return evaluationCriteria;
    }

    public void setEvaluationCriteria(Map<String, Integer> evaluationCriteria) {
        this.evaluationCriteria = evaluationCriteria;
    }
}