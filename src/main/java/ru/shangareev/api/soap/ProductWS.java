package ru.shangareev.api.soap;

import ru.shangareev.dto.ProductDto;
import ru.shangareev.services.ProductService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.shangareev.api.soap.IProduct")
public class ProductWS implements IProduct {

    @Inject
    private ProductService productService;

    @Override
    public List<ProductDto> getProducts(){
        return productService.getProductList();
    }

    @Override
    public ProductDto getProductById(int productId) {
        return productService.getProductById(productId);
    }
}
