public class Produtos {
    public String ProductID, ProductName, SupplierID, CategoryID, 
            QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, 
            ReorderLevel;
    public int Discontinued;
    
    public Produtos(){
    }

    public Produtos(String ProductID, String ProductName, String SupplierID, String CategoryID, String QuantityPerUnit, String UnitPrice, String UnitsInStock, String UnitsOnOrder, String ReorderLevel, int Discontinued) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.SupplierID = SupplierID;
        this.CategoryID = CategoryID;
        this.QuantityPerUnit = QuantityPerUnit;
        this.UnitPrice = UnitPrice;
        this.UnitsInStock = UnitsInStock;
        this.UnitsOnOrder = UnitsOnOrder;
        this.ReorderLevel = ReorderLevel;
        this.Discontinued = Discontinued;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String SupplierID) {
        this.SupplierID = SupplierID;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getQuantityPerUnit() {
        return QuantityPerUnit;
    }

    public void setQuantityPerUnit(String QuantityPerUnit) {
        this.QuantityPerUnit = QuantityPerUnit;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(String UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public String getUnitsInStock() {
        return UnitsInStock;
    }

    public void setUnitsInStock(String UnitsInStock) {
        this.UnitsInStock = UnitsInStock;
    }

    public String getUnitsOnOrder() {
        return UnitsOnOrder;
    }

    public void setUnitsOnOrder(String UnitsOnOrder) {
        this.UnitsOnOrder = UnitsOnOrder;
    }

    public String getReorderLevel() {
        return ReorderLevel;
    }

    public void setReorderLevel(String ReorderLevel) {
        this.ReorderLevel = ReorderLevel;
    }

    public int getDiscontinued() {
        return Discontinued;
    }

    public void setDiscontinued(int Discontinued) {
        this.Discontinued = Discontinued;
    }
    
    
    
}
