package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.Future;

public interface LotteryService {
	Future<List<Integer>> draw(int max,int size);
	List<List<Integer>> draw(int max,int size,int column);
}
