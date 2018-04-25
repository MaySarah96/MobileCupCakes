/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


/**
 *
 * @author escobar
 */
public class TestMoney {

    private Integer id;
    private int priceAmount;
    private String priceCurrency;

    public TestMoney() {
    }

    public TestMoney(Integer id) {
        this.id = id;
    }

    public TestMoney(Integer id, int priceAmount, String priceCurrency) {
        this.id = id;
        this.priceAmount = priceAmount;
        this.priceCurrency = priceCurrency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(int priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestMoney)) {
            return false;
        }
        TestMoney other = (TestMoney) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.TestMoney[ id=" + id + " ]";
    }
    
}
