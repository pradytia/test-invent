package controller;

import adapter.ProductAdapter;
import model.mdlProduct;
import model.mdlResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

    @RequestMapping(value = "/get-product-list", method = RequestMethod.GET)
    public @ResponseBody
    mdlResult GetProductList(){
        mdlResult _mdlResult = new mdlResult();
        List<mdlProduct> _mdlProductList = new ArrayList<>();

        _mdlProductList = ProductAdapter.GetProductList();

        if(_mdlProductList != null){
            _mdlResult.statusCode = "ERR-00-000";
            _mdlResult.statusText = "Success";
            _mdlResult.statusInfo = "Data tersedia";
            _mdlResult.value = _mdlProductList;
        }else {
            _mdlResult.statusCode = "ERR-00-001";
            _mdlResult.statusText = "Success";
            _mdlResult.statusInfo = "Data kosong";
            _mdlResult.value = null;
        }


        return  _mdlResult;
    }

}
