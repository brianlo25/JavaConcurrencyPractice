package com.example.demo.javaconcurrency.chapter11.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    CommentService commentService;
    @Autowired
    RepoService repoService;

    @GetMapping("/goods")
    public List<Goods> goods() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Goods>> cf = CompletableFuture.supplyAsync(() -> goodsService.queryGoods())
                .thenApplyAsync(goods -> {
            goods.stream().map(goods1 -> CompletableFuture.supplyAsync(() -> {
                goods1.setRepo(repoService.getRepoByGoodsId(goods1.getId()));
                return goods1;
            }).thenCompose(goods2 -> CompletableFuture.supplyAsync(() -> {
                goods2.setComment(commentService.getCommentsByGoodsId(goods2.getId()));
                return goods2;
            }))).toArray(CompletableFuture[]::new);
            return goods;
        });
        return (List<Goods>) cf.handleAsync((goods, th) -> th != null ? "系統繁忙" : goods).get();
    }

}
