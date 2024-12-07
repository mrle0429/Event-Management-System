package ucd.comp3013j.ems.model.entities;

import ucd.comp3013j.ems.model.enums.TicketType;

import java.math.BigDecimal;

/**
 * 用于表示某个票种的售出数量和价格。这个类通常用于表示一个 Event 中不同票种的售出情况和价格信息。
 */
public class TicketQuantity {
    private Long id;
    private TicketType ticketType;
    private BigDecimal price;
    private int numberSold;
}
