/**
 * output package name
 */
package com.kingdee.eas.custom.financialinvoice.app;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import com.kingdee.util.enums.StringEnum;

/**
 * output class name
 */
public class DeleteEnum extends StringEnum
{
    public static final String NORMAL_VALUE = "1";
    public static final String DELETE_VALUE = "2";

    public static final DeleteEnum normal = new DeleteEnum("normal", NORMAL_VALUE);
    public static final DeleteEnum delete = new DeleteEnum("delete", DELETE_VALUE);

    /**
     * construct function
     * @param String deleteEnum
     */
    private DeleteEnum(String name, String deleteEnum)
    {
        super(name, deleteEnum);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static DeleteEnum getEnum(String deleteEnum)
    {
        return (DeleteEnum)getEnum(DeleteEnum.class, deleteEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(DeleteEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(DeleteEnum.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(DeleteEnum.class);
    }
}