package com.shop.shop_inventory;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class TableTransformer {
    public TableTransformer() {
    }
    public String getTableHtmlString(List<Item> items) {
        if (items.isEmpty()){
            try (Scanner scanner = new Scanner(Paths.get("src/main/resources/static/zeroResults.html"))) {
                StringBuilder boilerplate = new StringBuilder();
                while (scanner.hasNextLine()) {
                    boilerplate.append(scanner.nextLine());
                }
                return boilerplate.toString();
            } catch (Exception e){
                System.out.println(e.getMessage());
                return "Error Loading Page";
            }
        }
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sb.append("<table><tr>");
        sb.append("<th>ID</th><th>Name</th><th>Expiry</th><th>Quantity</th><th>Price (£)</th></tr>" );
        for (Item item : items) {
            sb.append("<tr>");
            sb.append("<td>").append(item.getId()).append("</td>");
            sb.append("<td>").append(item.getName()).append("</td>");
            sb.append("<td>").append(sdf.format(item.getExpiry())).append("</td>");
            sb.append("<td>").append(item.getQuantity()).append("</td>");
            sb.append("<td>").append(item.getPrice() / 100).append(".").append(item.getPrice() - (100 * item.getPrice() / 100));
            if (item.getPrice() - (100 * item.getPrice() / 100) < 10){
                sb.append("0");
            }
            sb.append("</td>").append("</tr>");
        }
        sb.append("</table>");
        try (Scanner scanner = new Scanner(Paths.get("src/main/resources/static/Boilerplate.html"))) {
            StringBuilder boilerplate = new StringBuilder();
            while (scanner.hasNextLine()) {
                boilerplate.append(scanner.nextLine());
            }
            String result = boilerplate.toString();
            result = result.replaceFirst("Error Loading Page.",sb.toString());
            return result;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return "Error Loading Page";
        }
    }
}
