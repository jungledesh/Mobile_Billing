
package cellphonebill;

 class Plan 
    {
    private String name ; 
     private String data ; 
     private String chargePerGb ; 
        
     Plan(String name, String data, String chargePerGb)
     {  
    setName(name); 
    setData(data);
    setChargePerGb(chargePerGb);
     }
     
     //getters
     public String getName(){ return name; }
     public String getData(){ return data; } 
     public String getChargePerGb() { return chargePerGb ; } 
     
     //setters 
    public void setName(String nam){name = nam ; } 
     public void setData (String dta) {data=dta ; } 
     public void setChargePerGb(String chargeGb) { chargePerGb = chargeGb ; } 
    
    }