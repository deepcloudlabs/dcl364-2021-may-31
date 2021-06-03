package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface LotteryService {
	CompletableFuture<List<Integer>> draw(int max,int size);
	List<Integer> functionalDraw(int max,int size);
	List<List<Integer>> draw(int max,int size,int column);
}
