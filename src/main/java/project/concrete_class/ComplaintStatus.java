package project.concrete_class;


public enum ComplaintStatus {
    NEW("New"),
    UNDER_REVIEW("Under Review"),
    ASSIGNED("Assigned"),
    RESOLVED("Resolved"),
    ON_HOLD("On Hold"),
    WITHDRAWN("Withdrawn");
 
    private final String description;
    
    private ComplaintStatus(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
}
