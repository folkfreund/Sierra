/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.httprpc.sierra;

import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract base class for layout panels.
 */
public abstract class LayoutPanel extends JPanel implements Scrollable {
    abstract static class AbstractLayoutManager implements LayoutManager2 {
        private Map<Component, Object> constraints = new HashMap<>();

        @Override
        public void addLayoutComponent(String name, Component component) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addLayoutComponent(Component component, Object constraints) {
            this.constraints.put(component,  constraints);
        }

        @Override
        public void removeLayoutComponent(Component component) {
            constraints.remove(component);
        }

        @Override
        public void invalidateLayout(Container container) {
            // No-op
        }

        @Override
        public float getLayoutAlignmentX(Container container) {
            return 0;
        }

        @Override
        public float getLayoutAlignmentY(Container container) {
            return 0;
        }

        @Override
        public Dimension minimumLayoutSize(Container container) {
            return new Dimension(0, 0);
        }

        @Override
        public Dimension maximumLayoutSize(Container container) {
            return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        @Override
        public Dimension preferredLayoutSize(Container container) {
            return preferredLayoutSize();
        }

        protected abstract Dimension preferredLayoutSize();

        @Override
        public void layoutContainer(Container container) {
            layoutContainer();
        }

        protected abstract void layoutContainer();

        protected Object getConstraints(Component component) {
            return constraints.get(component);
        }
    }

    private boolean scrollableTracksViewportWidth = false;
    private boolean scrollableTracksViewportHeight = false;

    private boolean ignoreInvalidate = false;

    LayoutPanel() {
        super(null);

        setOpaque(false);
    }

    /**
     * Return's the panel's preferred size.
     * {@inheritDoc}
     */
    @Override
    public Dimension getPreferredSize() {
        ignoreInvalidate = true;

        var preferredSize = super.getPreferredSize();

        ignoreInvalidate = false;

        return preferredSize;
    }

    /**
     * Invalidates the panel.
     * {@inheritDoc}
     */
    @Override
    public void invalidate() {
        if (ignoreInvalidate) {
            return;
        }

        super.invalidate();
    }

    /**
     * Returns the panel's preferred scrollable viewport size.
     * {@inheritDoc}
     */
    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    /**
     * Returns the panel's scrollable unit increment.
     * {@inheritDoc}
     */
    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        if (visibleRect == null) {
            throw new IllegalArgumentException();
        }

        switch (orientation) {
            case SwingConstants.VERTICAL: {
                return visibleRect.height / 10;
            }

            case SwingConstants.HORIZONTAL: {
                return visibleRect.width / 10;
            }

            default: {
                throw new UnsupportedOperationException();
            }
        }
    }

    /**
     * Returns the panel's scrollable block increment.
     * {@inheritDoc}
     */
    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        if (visibleRect == null) {
            throw new IllegalArgumentException();
        }

        switch (orientation) {
            case SwingConstants.VERTICAL: {
                return visibleRect.height;
            }

            case SwingConstants.HORIZONTAL: {
                return visibleRect.width;
            }

            default: {
                throw new UnsupportedOperationException();
            }
        }
    }
}
