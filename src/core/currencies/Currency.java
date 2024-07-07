package core.currencies;

public class Currency {
    private Double valueRelativeToPLN;
    private String code;
    private String currency;
    public Currency(){}
    public Currency(String code, String currency, Double valueRelativeToPLN){
        this.code=code;
        this.currency=currency;
        this.valueRelativeToPLN=valueRelativeToPLN;
    }

    public void setValueRelativeToPLN(Double valueRelativeToPLN) {
        this.valueRelativeToPLN = valueRelativeToPLN;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public Double getValueRelativeToPLN() {
        return valueRelativeToPLN;
    }

    public String getCode() {
        return code;
    }

    public String getCurrency() {
        return currency;
    }
}
