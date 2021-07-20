public class feed {

    int feedback_id;
    String user,company,written;

    public feed(int feedback_id,String u,String c,String w){
        this.feedback_id=feedback_id;
        this.user=u;
        this.company=c;
        this.written=w;
    }

    public feed(String user,String w){
        this.user=user;
        this.written=w;
    }

    public void setFeedback_id(int id){
        this.feedback_id=id;
    }

    public void setuser(String u){
        this.user=u;
    }

    public void setcompany(String c){
        this.company=c;
    }

    public void setwritten(String written){
        this.written=written;
    }

    public int getFeedback_id(){
        return feedback_id;
    }

    public String getUser(){
        return user;
    }

    public String getCompany(){
        return company;
    }

    public String getWritten(){
        return written;
    }



    
}
