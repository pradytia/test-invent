package adapter;

import database.DatabaseAdapter;
import model.mdlProduct;

import javax.sql.rowset.CachedRowSet;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter {

    public static List<mdlProduct> GetProductList (){

        String sql  = "";
        List<mdlProduct> _mdlProductList = new ArrayList<>();
        CachedRowSet rowSet =  null;

        try{
            sql = "SELECT * FROM product";

            rowSet = DatabaseAdapter.getExecute(sql);

            while (rowSet.next()){
                mdlProduct _mdlProduct =  new mdlProduct();

                _mdlProduct.ID = rowSet.getString("id");
                _mdlProduct.nama_barang = rowSet.getString("nama_barang");
                _mdlProduct.kode_barang = rowSet.getString("kode_barang");
                _mdlProduct.jumlah_barang = rowSet.getString("jumlah_barang");
                _mdlProduct.tanggal = rowSet.getString("tanggal");

                _mdlProductList.add(_mdlProduct);

            }

        }catch (Exception ex){
            _mdlProductList = null;
            ex.getMessage();
        }

        return _mdlProductList;
    }
}
