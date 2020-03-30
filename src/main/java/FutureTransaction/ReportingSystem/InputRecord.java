package FutureTransaction.ReportingSystem;

public class InputRecord {

	
	public String clientId;
	public String productId; 
	public int netTransactionAmount;
	
	public InputRecord(){
		
	}
	public InputRecord(String c, String p, int t){
		clientId = c;
		productId = p;
		netTransactionAmount = t;
	}
	public void setNetTransactionAmount(String qtyLong, String qtyShort){
		int qtylong = Integer.parseInt(qtyLong);
		int qtyshort = Integer.parseInt(qtyShort);
		netTransactionAmount = qtylong - qtyshort;
	}
	public int getNetTranactionAmount(){
		return netTransactionAmount;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	@Override public String toString() { 
		return String.format("%s %s %s", clientId,productId,String.valueOf(netTransactionAmount));
		}
	
}
