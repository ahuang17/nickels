package com.cta;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CtaController {

    public ArrayList<Integer> custWallet = new ArrayList<Integer>();

	@GetMapping("/checkFare/{fare}")
	public int getNumFareOptions(@PathVariable int fare) {
        return fareWays(custWallet, fare);
	}

    @GetMapping("/addMoney/{money}")
    public void addMoney(@PathVariable String money) {
        String[] parts = money.split(",");
        for (int i = 0; i < parts.length; i++) {
        custWallet.add(Integer.parseInt(parts[i]));
        }
    }

    @GetMapping("/checkWallet")
	public String getCustWallet() throws Exception{
        try{
            return custWallet.stream().map(Object::toString).collect(Collectors.joining(","));
        } catch (Exception e) {
            return "Wallet Empty";
        }
	}

	public static int fareWays(ArrayList<Integer> wallet, int sum) {

        HashMap formattedWallet = formatWallet(wallet);

        int[][] map = new int[wallet.size() + 1][sum + 1];

        map[0][0] = 1;
        for (int j = 1; j <= wallet.size(); j++) {
            for (int i = 0; i <= sum; i++) {
                map[j][i] += map[j - 1][i];
            }
            //printArray(map, wallet.length, sum);

            for (int k = 1; k <= (int) formattedWallet.get(wallet.get(j-1)); k++) {
                int initial = wallet.get(j - 1) * k;
                for (int i = initial; i <= sum; i++) {
                    map[j][i] += map[j - 1][i - initial];
                    //printArray(map, wallet.length, sum);
                }
            }
        }
        return map[wallet.size()][sum];
    }

    public static int showFareWays(ArrayList<Integer> wallet, int sum) {

        HashMap formattedWallet = formatWallet(wallet);

        int[][] map = new int[wallet.size() + 1][sum + 1];

        map[0][0] = 1;
        for (int j = 1; j <= wallet.size(); j++) {
            for (int i = 0; i <= sum; i++) {
                map[j][i] += map[j - 1][i];
            }
            //printArray(map, wallet.length, sum);

            for (int k = 1; k <= (int) formattedWallet.get(wallet.get(j-1)); k++) {
                int initial = wallet.get(j - 1) * k;
                for (int i = initial; i <= sum; i++) {
                    map[j][i] += map[j - 1][i - initial];
                    //printArray(map, wallet.length, sum);
                }
            }
        }
        return map[wallet.size()][sum];
    }

    public static void printArray(int[][] map, int n, int sum) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println();
    }

    public static HashMap<Integer, Integer> formatWallet(ArrayList<Integer> wallet) {
        Collections.sort(wallet);
        HashMap<Integer, Integer> counted = new HashMap<Integer, Integer>();
        for (Integer coin : wallet) {
            if (counted.containsKey(coin)) {
                counted.put(coin, counted.get(coin) + 1);
            } else {
                counted.put(coin, 1);
            }
        }
        return counted;
    }
}
