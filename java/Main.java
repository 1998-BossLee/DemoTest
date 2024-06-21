/**
 * @author: liyangjin
 * @create: 2024-05-20 10:07
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        String s = "select concat(toString(mediaId), '::', toString(dspGroupId), '::', bundleId, '::', action) AS key,\n" +
                "    COUNT(1) AS value\n" +
                "from (\n" +
                "select \n" +
                "    a.mediaId as mediaId,\n" +
                "    a.dspGroupId as dspGroupId,\n" +
                "    a.bundleId as bundleId,\n" +
                "    a.action as action\n" +
                "from minidsp.adx_effect_all a\n" +
                "global join \n" +
                "    (\n" +
                "        select \n" +
                "            traceId,action\n" +
                "        from minidsp.ocpx_effect_all \n" +
                "        where dt >= '${startTime}'\n" +
                "        and dt <= '${endTime}'\n" +
                "        and (source = 'mini_adx' or target = 'mini_adx') \n" +
                "        and action in ('clk')\n" +
                "    ) as c\n" +
                "on a.traceId = c.traceId \n" +
                "where a.dt >= '${startTime}' \n" +
                "and a.dt <= '${endTime}' \n" +
                "and a.bundleId != ''\n" +
                "and a.dspGroupId in ${bundleIdDspGroupIdsString}\n" +
                "and a.action = 'clk' \n" +
                ") \n" +
                "group by key\n";
        System.out.println(s);
    }
}
