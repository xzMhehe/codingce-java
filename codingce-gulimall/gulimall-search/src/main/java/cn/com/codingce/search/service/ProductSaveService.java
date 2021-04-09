package cn.com.codingce.search.service;

import cn.com.codingce.common.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * @author williamma
 */
public interface ProductSaveService {

    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;

}
