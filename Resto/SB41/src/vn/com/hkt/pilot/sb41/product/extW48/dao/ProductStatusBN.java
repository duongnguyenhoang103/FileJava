/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb41.product.extW48.dao;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb41.generic.jpas.utils.PersistenceUtility;
import vn.com.hkt.pilot.sb41.product.extW48.entity.ProductStatus;

/**
 *
 * @author duong
 */
public class ProductStatusBN extends AccessData<ProductStatus> {

    public ProductStatusBN() {
        setAccessData(PersistenceUtility.getEmf(), ProductStatus.class);
    }
}
