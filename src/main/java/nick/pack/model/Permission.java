package nick.pack.model;

public enum Permission {
    CRUD("crud"),
    BLOCKING("blocking"),
    VIEWUSER("viewuser");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
