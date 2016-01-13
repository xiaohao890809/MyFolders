package com.jk.learnadapter.model;

public class Good
{
    private String name;
    private int imgRes;
    private int count;
    private float price;

    public Good(String name,int imgRes,int count,float price)
    {
        setName(name);
        setImgRes(imgRes);
        setCount(count);
        setPrice(price);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getImgRes()
    {
        return imgRes;
    }

    public void setImgRes(int imgRes)
    {
        this.imgRes = imgRes;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }
}
