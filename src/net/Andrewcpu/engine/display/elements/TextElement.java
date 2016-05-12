package net.Andrewcpu.engine.display.elements;

import net.Andrewcpu.engine.world.Renderable;

import java.awt.*;

/**
 * Created by stein on 5/12/2016.
 */
public class TextElement extends Element implements Renderable {
    private String text, fontName = "TimesRoman";
    private int fontSize = 5;
    private Font font;
    private Color textColor = Color.BLACK;
    public TextElement(String text, int x, int y) {
        this.text = text;
        setX(x);
        setY(y);
        setupFont();
    }

    public TextElement(String text, int x, int y, int fontSize) {
        this.text = text;
        setX(x);
        setY(y);
        this.fontSize = fontSize;
        setupFont();
    }
    public TextElement(String text, int x, int y, int fontSize, String fontName) {
        this.text = text;
        setX(x);
        setY(y);
        this.fontSize = fontSize;
        this.fontName = fontName;
        setupFont();
    }
    private void setupFont(){
        font = (new Font(fontName, Font.PLAIN, fontSize));
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
        setupFont();
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        setupFont();
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(getFont());
        g.setColor(getTextColor());
        g.drawString(getText(),getX(),getY());
    }
}
