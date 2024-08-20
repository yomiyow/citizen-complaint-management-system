package project.concrete_class;


public enum AccountStatus {
    ACTIVE("Active"),
    SUSPENDED("Under Review");
 
    private final String description;
    
    private AccountStatus(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
}
