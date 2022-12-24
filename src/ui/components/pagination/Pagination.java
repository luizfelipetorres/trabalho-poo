package ui.components.pagination;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Pagination extends JPanel {

    public PaginationItemRender getPaginationItemRender() {
        return paginationItemRender;
    }

    public void setPaginationItemRender(PaginationItemRender paginationItemRender) {
        this.paginationItemRender = paginationItemRender;
        changePage(page.getCurrent(), page.getTotalPage());
    }

    private PaginationItemRender paginationItemRender;
    private List<EventPagination> events = new ArrayList<>();
    private Page page;

    public Pagination() {
        init();
    }

    private void init() {
        paginationItemRender = new DefaultPaginationItemRender();
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
        setPagegination(1, 1);
    }

    private void runEvent() {
        for (EventPagination event : events) {
            event.pageChanged(page.getCurrent());
        }
    }

    private boolean isEnable(Object item) {
        return (item instanceof Page.BreakLabel || Integer.valueOf(item.toString()) != page.getCurrent());
    }

    public void addEventPagination(EventPagination event) {
        events.add(event);
    }

    public void setPagegination(int current, int totalPage) {
        if (current > totalPage) {
            current = totalPage;
        }
        if (page == null || (page.getCurrent() != current || page.getTotalPage() != totalPage)) {
            changePage(current, totalPage);
        }
    }

    private void changePage(int current, int totalPage) {
    	int aux = 1;
    	
        page = paginate(current, totalPage);
        removeAll();
        refresh();
        JButton cmdPrev = paginationItemRender.createPaginationItem("Previous", true, false, page.isPrevious());
        cmdPrev.setBackground(Color.LIGHT_GRAY);
        cmdPrev.setForeground(Color.black);
        
        if(current == 1) {
        	cmdPrev.setVisible(false);
        }
        
        cmdPrev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cmdPrev.setBackground(Color.DARK_GRAY);
				cmdPrev.setForeground(Color.white);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cmdPrev.setBackground(Color.LIGHT_GRAY);
				cmdPrev.setForeground(Color.black);
			}
		});
        
        cmdPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (page.getCurrent() > 1) {
                    setPagegination(page.getCurrent() - 1, totalPage);
                    runEvent();
                }
            }
        });
        add(cmdPrev);
        
        
        for (Object item : page.getItems()) {
            JButton cmd = paginationItemRender.createPaginationItem(item, false, false, isEnable(item));
            
            if(aux == current) {
            	cmd.setBackground(Color.DARK_GRAY);
				cmd.setForeground(Color.white);
            }else {
            	
            	cmd.setBackground(Color.LIGHT_GRAY);
            	cmd.setForeground(Color.black);
            }
            aux++;
            
            if (item instanceof Integer) {
                if (Integer.valueOf(item.toString()) == page.getCurrent()) {
                    cmd.setSelected(true);
                }
            }
            
            cmd.addMouseListener(new MouseAdapter() {
    			@Override
    			public void mouseEntered(MouseEvent e) {
    				cmd.setBackground(Color.DARK_GRAY);
    				cmd.setForeground(Color.white);
    				
    			}

    			@Override
    			public void mouseExited(MouseEvent e) {
    				cmd.setBackground(Color.LIGHT_GRAY);
    				cmd.setForeground(Color.black);
    			}
    		});
    		
            
            cmd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!cmd.isSelected() && item != null) {
                        if (item instanceof Page.BreakLabel) {
                            Page.BreakLabel pb = (Page.BreakLabel) item;
                            setPagegination(pb.getPage(), totalPage);
                        } else {
                            setPagegination(Integer.valueOf(item.toString()), totalPage);
                        }
                        runEvent();
                    }
                }
            });
            add(cmd);
        }
        
        
        JButton cmdNext = paginationItemRender.createPaginationItem("Next", false, true, page.isNext());
        
        cmdNext.setBackground(Color.LIGHT_GRAY);
        cmdNext.setForeground(Color.black);
        
        if(current == totalPage) {
        	cmdNext.setVisible(false);
        }
        
        cmdNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cmdNext.setBackground(Color.DARK_GRAY);
				cmdNext.setForeground(Color.white);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cmdNext.setBackground(Color.LIGHT_GRAY);
				cmdNext.setForeground(Color.black);
			}
		});
        
        cmdNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (page.getCurrent() < page.getTotalPage()) {
                    setPagegination(page.getCurrent() + 1, totalPage);
                    runEvent();
                }
            }
        });
        add(cmdNext);
    }

    private void refresh() {
        repaint();
        revalidate();
    }

    private Page paginate(int current, int max) {
        boolean prev = current > 1;
        boolean next = current < max;
        List<Object> items = new ArrayList<>();
        items.add(1);
        if (current == 1 && max == 1) {
            return new Page(current, prev, next, items, max);
        }
        int r = 2;
        int r1 = current - r;
        int r2 = current + r;
        if (current > 4) {
            items.add(new Page.BreakLabel((r1 > 2 ? r1 : 2) - 1));
        }
        for (int i = r1 > 2 ? r1 : 2; i <= Math.min(max, r2); i++) {
            items.add(i);
        }
        if (r2 + 1 < max) {
            items.add(new Page.BreakLabel(Integer.valueOf(items.get(items.size() - 1).toString()) + 1));
        }
        if (r2 < max) {
            items.add(max);
        }
        return new Page(current, prev, next, items, max);
    }
}