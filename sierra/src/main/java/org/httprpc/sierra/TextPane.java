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

import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Displays a string of text.
 */
public class TextPane extends JComponent {
    private String text;

    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEADING;
    private VerticalAlignment verticalAlignment = VerticalAlignment.TOP;

    private boolean wrapText = true;

    private BaselineOption baselineOption = BaselineOption.FIRST;

    /**
     * Constructs a text pane.
     */
    public TextPane() {
        this(null);
    }

    /**
     * Constructs a text pane.
     *
     * @param text
     * The text to display, or {@code null} for no text.
     */
    public TextPane(String text) {
        this.text = text;
    }

    /**
     * Returns the text displayed by the component.
     *
     * @return
     * The text displayed by the component.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text displayed by the component.
     *
     * @param text
     * The text to display, or {@code null} for no text.
     */
    public void setText(String text) {
        this.text = text;

        revalidate();
    }

    /**
     * Returns the horizontal alignment.
     *
     * @return
     * The horizontal alignment.
     */
    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    /**
     * Sets the horizontal alignment.
     *
     * @param horizontalAlignment
     * The horizontal alignment.
     */
    public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        if (horizontalAlignment == null) {
            throw new IllegalArgumentException();
        }

        this.horizontalAlignment = horizontalAlignment;

        repaint();
    }

    /**
     * Returns the vertical alignment.
     *
     * @return
     * The vertical alignment.
     */
    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    /**
     * Sets the vertical alignment.
     *
     * @param verticalAlignment
     * The vertical alignment.
     */
    public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
        if (verticalAlignment == null) {
            throw new IllegalArgumentException();
        }

        this.verticalAlignment = verticalAlignment;

        repaint();
    }

    /**
     * Indicates that line wrapping is enabled.
     *
     * @return
     * {@code true} if the text will wrap when needed; {@code false},
     * otherwise.
     */
    public boolean getWrapText() {
        return wrapText;
    }

    /**
     * Toggles line wrapping.
     *
     * @param wrapText
     * {@code true} to wrap text when needed; {@code false}, otherwise.
     */
    public void setWrapText(boolean wrapText) {
        this.wrapText = wrapText;

        revalidate();
    }

    /**
     * Returns the text pane's baseline option.
     *
     * @return
     * The text pane's baseline option.
     */
    public BaselineOption getBaselineOption() {
        return baselineOption;
    }

    /**
     * Sets the text pane's baseline option.
     *
     * @param baselineOption
     * The text pane's baseline option.
     */
    public void setBaselineOption(BaselineOption baselineOption) {
        if (baselineOption == null) {
            throw new IllegalArgumentException();
        }

        this.baselineOption = baselineOption;
    }

    /**
     * Returns 0, 0.
     * {@inheritDoc}
     */
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(0, 0);
    }

    /**
     * Returns {@link Integer#MAX_VALUE}, {@link Integer#MAX_VALUE}.
     * {@inheritDoc}
     */
    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Returns the text pane's preferred size.
     * {@inheritDoc}
     */
    @Override
    public Dimension getPreferredSize() {
        // TODO Return constrained text size
        return new Dimension();
    }

    /**
     * Returns the text pane's baseline.
     * {@inheritDoc}
     */
    @Override
    public int getBaseline(int width, int height) {
        // TODO Take wrapping and vertical alignment into account
        return -1;
    }

    /**
     * Paints the text pane.
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        // TODO Don't make permanent changes to the GC
    }
}
