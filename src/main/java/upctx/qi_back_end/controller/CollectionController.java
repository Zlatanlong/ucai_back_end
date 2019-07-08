package upctx.qi_back_end.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upctx.qi_back_end.domain.CollectionDish;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.service.CollectionService;

@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @GetMapping("/change")
    public Result<CollectionDish> change(Integer userid,
                                         Integer dishid) {
        return collectionService.changeDishCollection(userid, dishid);
    }
}
