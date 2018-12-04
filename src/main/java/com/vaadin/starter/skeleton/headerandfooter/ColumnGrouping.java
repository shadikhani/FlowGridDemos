package com.vaadin.starter.skeleton.headerandfooter;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.FooterRow;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Benefit;

@Route("ColumnGrouping")
public class ColumnGrouping extends VerticalLayout {

	public ColumnGrouping() {

		List<Benefit> benefitList = new ArrayList<>();
		benefitList.add(new Benefit(2017, 1000, 2000, 1000, 2000));
		benefitList.add(new Benefit(2018, 100, 200, 300, 400));
		benefitList.add(new Benefit(2019, 1, 2, 3, 4));
		benefitList.add(new Benefit(2019, 1, 2, 3, 4));
		benefitList.add(new Benefit(2019, 1, 2, 3, 4));
		benefitList.add(new Benefit(2019, 1, 2, 3, 4));
		benefitList.add(new Benefit(2019, 1, 2, 3, 4));
		benefitList.add(new Benefit(2019, 1, 2, 3, 4));
		

		Grid<Benefit> grid = new Grid<>();
		grid.setItems(benefitList);

		Column<Benefit> year = grid.addColumn(Benefit::getYear).setHeader("Year");
		//Setting the alignment of columns
		Column<Benefit> quarter1 = grid.addColumn(Benefit::getQuarter1, "").setHeader("Quarter 1").setTextAlign(ColumnTextAlign.CENTER);
		Column<Benefit> quarter2 = grid.addColumn(Benefit::getQuarter2, "").setHeader("Quarter 2").setTextAlign(ColumnTextAlign.CENTER);
		Column<Benefit> quarter3 = grid.addColumn(Benefit::getQuarter3, "").setHeader("Quarter 3");
		Column<Benefit> quarter4 = grid.addColumn(Benefit::getQuarter4, "").setHeader("Quarter 4");
		
		
		HeaderRow halfheaderRow = grid.prependHeaderRow();
        
		//Setting the alignmemt of only the header 
		Div half1Header = new Div(new Span("Half 1"));
		half1Header.getStyle().set("text-align", "center");
		half1Header.setSizeFull();
		
		//Setting the alignmemt of only the header 
		Div half2Header = new Div(new Span("Half 2"));
		half2Header.getStyle().set("text-align", "center");
		half2Header.setSizeFull();
		
		
		halfheaderRow.join(quarter1, quarter2).setComponent(half1Header);
//		halfheaderRow.join(quarter2, quarter2).setComponent(half2Header);
//		halfheaderRow.join(quarter3, quarter4).setText("Half 2");
		
		

//		HeaderRow yearRow = grid.prependHeaderRow();
//		yearRow.join(yearRow.getCells().get(1), yearRow.getCells().get(2)).setText("Year");


		int sumQuarter1 = benefitList.stream().mapToInt(Benefit::getQuarter1).sum();
		int sumQuarter2 = benefitList.stream().mapToInt(Benefit::getQuarter2).sum();
		int sumQuarter3 = benefitList.stream().mapToInt(Benefit::getQuarter3).sum();
		int sumQuarter4 = benefitList.stream().mapToInt(Benefit::getQuarter4).sum();
		grid.appendFooterRow();
		FooterRow halfFooterRow = grid.appendFooterRow();

		halfFooterRow.join(quarter1, quarter2).setText(String.valueOf(sumQuarter1 + sumQuarter2));
		halfFooterRow.join(quarter3, quarter4).setText(String.valueOf(sumQuarter3 + sumQuarter4));
		
		
		

		FooterRow footerYearRow = grid.appendFooterRow();
		footerYearRow.join(footerYearRow.getCells().get(1), footerYearRow.getCells().get(2))
				.setText(String.valueOf(sumQuarter1 + sumQuarter2 + sumQuarter3 + sumQuarter4));
		

		add(grid);

	}

}
