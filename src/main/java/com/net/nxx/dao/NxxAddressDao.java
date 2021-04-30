package com.net.nxx.dao;

import com.net.nxx.model.NxxAddress;

import java.util.List;

public interface NxxAddressDao {
    int deleteByPrimaryKeyAndUser(Long id,Long userId);

    int insert(NxxAddress record);

    int insertSelective(NxxAddress record);

    NxxAddress selectByPrimaryKey(Long id);

    List<NxxAddress> getAddressByUser(Long userId);

    List<NxxAddress> getDefaultAddress(Long userId);

    int updateByPrimaryKeySelective(NxxAddress record);

    int updateByUserIdSelective(NxxAddress record);

    int updateByPrimaryKey(NxxAddress record);
}
