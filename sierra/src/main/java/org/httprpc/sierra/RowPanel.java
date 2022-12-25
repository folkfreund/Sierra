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

import java.awt.Dimension;
import java.awt.LayoutManager;

/**
 * Arranges sub-components horizontally in a row, optionally pinning component
 * edges to the container's top and bottom insets.
 */
public class RowPanel extends BoxPanel {
    // Row layout manager
    private class RowLayoutManager extends BoxLayoutManager {
        @Override
        public Dimension preferredLayoutSize() {
            var preferredWidth = 0;
            var totalWeight = 0.0;

            var n = getComponentCount();

            for (var i = 0; i < n; i++){
                var component = getComponent(i);

                var weight = getWeight(component);

                if (Double.isNaN(weight)) {
                    component.setSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
                    component.setSize(component.getPreferredSize());

                    preferredWidth += component.getWidth();
                } else {
                    totalWeight += weight;
                }
            }

            preferredWidth += getSpacing() * (n - 1);

            var size = getSize();
            var insets = getInsets();

            var remainingWidth = Math.max(size.width - (insets.left + insets.right) - preferredWidth, 0);

            var preferredHeight = 0;

            var maximumAscent = 0;
            var maximumDescent = 0;

            for (var i = 0; i < n; i++){
                var component = getComponent(i);

                var weight = getWeight(component);

                if (!Double.isNaN(weight)) {
                    var width = (int)Math.round(remainingWidth * (weight / totalWeight));

                    component.setSize(width, Integer.MAX_VALUE);
                    component.setSize(width, component.getPreferredSize().height);
                }

                preferredHeight = Math.max(preferredHeight, component.getHeight());

                if (alignToBaseline) {
                    var height = component.getHeight();

                    var baseline = component.getBaseline(component.getWidth(), height);

                    if (baseline >= 0) {
                        maximumAscent = Math.max(maximumAscent, baseline);
                        maximumDescent = Math.max(maximumDescent, height - baseline);
                    }
                }
            }

            if (alignToBaseline) {
                preferredHeight = Math.max(maximumAscent + maximumDescent, preferredHeight);
            }

            return new Dimension(preferredWidth + insets.left + insets.right, preferredHeight + insets.top + insets.bottom);
        }

        @Override
        public void layoutContainer() {
            // TODO Add support for baseline alignment
            
            var size = getSize();
            var insets = getInsets();

            var remainingWidth = Math.max(size.width - (insets.left + insets.right), 0);
            var totalWeight = 0.0;

            var height = Math.max(size.height - (insets.top + insets.bottom), 0);

            var n = getComponentCount();

            for (var i = 0; i < n; i++){
                var component = getComponent(i);

                var weight = getWeight(component);

                if (Double.isNaN(weight)) {
                    component.setSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
                    component.setSize(component.getPreferredSize().width, height);

                    remainingWidth -= component.getWidth();
                } else {
                    totalWeight += weight;
                }
            }

            var spacing = getSpacing();

            remainingWidth = Math.max(0, remainingWidth - spacing * (n - 1));

            var rightToLeft = !getComponentOrientation().isLeftToRight();

            var x = insets.left;

            if (rightToLeft) {
                x += size.width;
            }

            for (var i = 0; i < n; i++){
                var component = getComponent(i);

                var weight = getWeight(component);

                if (!Double.isNaN(weight)) {
                    component.setSize((int)Math.round(remainingWidth * (weight / totalWeight)), height);
                }

                if (rightToLeft) {
                    x -= component.getWidth();
                }

                component.setLocation(x, insets.top);

                if (rightToLeft) {
                    x -= spacing;
                } else {
                    x += component.getWidth() + spacing;
                }
            }
        }
    }

    private boolean alignToBaseline = false;

    /**
     * Constructs a new row panel.
     */
    public RowPanel() {
        setLayout(new RowLayoutManager());
    }

    /**
     * Sets the layout manager.
     * {@inheritDoc}
     */
    @Override
    public void setLayout(LayoutManager layoutManager) {
        if (layoutManager != null && !(layoutManager instanceof RowLayoutManager)) {
            throw new IllegalArgumentException();
        }

        super.setLayout(layoutManager);
    }

    /**
     * Indicates that sub-components will be aligned to baseline.
     *
     * @return
     * {@code true} to align to baseline; {@code false}, otherwise.
     */
    public boolean getAlignToBaseline() {
        return alignToBaseline;
    }

    /**
     * Toggles baseline alignment.
     *
     * @param alignToBaseline
     * {@code true} to align to baseline; {@code false}, otherwise.
     */
    public void setAlignToBaseline(boolean alignToBaseline) {
        this.alignToBaseline = alignToBaseline;

        revalidate();
    }

    /**
     * Calculates the panel's baseline.
     * {@inheritDoc}
     */
    @Override
    public int getBaseline(int width, int height) {
        if (getComponentCount() == 0 || !alignToBaseline) {
            return -1;
        }

        // TODO Calculate baseline
        return 0;
    }
}
