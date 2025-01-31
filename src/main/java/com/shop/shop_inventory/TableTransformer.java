package com.shop.shop_inventory;

import java.text.SimpleDateFormat;
import java.util.List;

public class TableTransformer {
    private final List<Item> items;
    public TableTransformer(List<Item> items) {
        this.items = items;;
    }
    public String getTableHtmlString() {
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
        String boilerPlate = """
                        <!DOCTYPE html>
                                <html lang="en">
                                <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <meta http-equiv="X-UA-Compatible" content="ie=edge">
                                <title>HTML 5 Boilerplate</title>
                                <link rel="stylesheet" href="/style.css">
                                </head>
                                <body>
                """ + sb + """
                                </body>
                                </html>
                """;
        return boilerPlate;
    }
}
