public class modelTable {
    int order_id,price;
    String name,house_no,street_name,area,item_name;
    
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHouse_no() {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }



    

    public modelTable(int oid,String n,String h_no,String s_name,String a){
        this.order_id=oid;
        this.name=n;
        this.house_no=h_no;
        this.street_name=s_name;
        this.area=a;
    }

    public modelTable(String name,int p){
        this.item_name=name;
        this.price=p;

    }

}
