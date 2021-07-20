public class Supplier {
    private int supplier_id;
    
    private String name,company_name,username,password;

    public Supplier(String name, String company_name, String username, String password) {
        this.name = name;
        this.company_name = company_name;
        this.username = username;
        this.password = password;
    }

    public Supplier(int supplier_id, String name, String company_name, String username, String password) {
        this.supplier_id = supplier_id;
        this.name = name;
        this.company_name = company_name;
        this.username = username;
        this.password = password;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
