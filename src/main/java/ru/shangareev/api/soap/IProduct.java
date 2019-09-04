package ru.shangareev.api.soap;

import ru.shangareev.dto.ProductDto;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;


@WebService
public interface IProduct{

    @WebMethod
    List<ProductDto> getProducts();

    ProductDto getProductById(int productId);
}