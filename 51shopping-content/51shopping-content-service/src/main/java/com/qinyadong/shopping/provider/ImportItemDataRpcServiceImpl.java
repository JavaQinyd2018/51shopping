package com.qinyadong.shopping.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.qinyadong.shopping.api.ImportItemDataRpcService;
import com.qinyadong.shopping.common.RpcCode;
import com.qinyadong.shopping.common.RpcResponse;
import com.qinyadong.shopping.entity.Item;
import com.qinyadong.shopping.es.ItemRepository;
import com.qinyadong.shopping.po.ItemPo;
import com.qinyadong.shopping.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2019/3/2
 */
@Service
@Slf4j
public class ImportItemDataRpcServiceImpl implements ImportItemDataRpcService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private Mapper mapper;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public RpcResponse<Void> importAllItemData() {
        RpcResponse<Void> response = new RpcResponse<>();
        List<Item> itemList = itemService.getAllItemList();
        log.info("获取的所有的item总的条数为：{}",itemList.size());
        try {
            List<ItemPo> itemPoList = itemList.stream()
                    .map(item -> mapper.map(item, ItemPo.class))
                    .collect(Collectors.toList());

            itemPoList.forEach(itemPo -> itemRepository.save(itemPo));
            response.setCode(RpcCode.RESPONSE_SUCCESS);
            response.setMessage("数据导入成功");
        } catch (Exception e) {
           log.error("错误信息为：{}",e);
           response.setCode(RpcCode.SYSTEM_RESPONSE_EXCEPTION);
           response.setMessage("数据导入失败");
        }
        return response;
    }
}
