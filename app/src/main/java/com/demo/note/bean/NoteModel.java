package com.demo.note.bean;

/**
 * @author haoyupeng
 * @description：
 * @time 2021/5/19
 */

import com.google.gson.annotations.SerializedName;

/**
 * "id": 1558061460522,
 * "title": "优胜劣汰",
 * "content": "非洲，每天早晨羚羊睁开眼睛，所想到的第一件事就是，我必须比跑得最快的狮子跑得更快，否则我会被吃掉；而就在同一时刻，狮子从睡梦中醒来，首先闪现在他脑海中的念头是，我必须能赶得上跑得最快的羚羊，要不然，我会饿死。于是，几乎同时，羚羊和狮子一跃而起，迎着朝阳跑去。这是生物圈里一个很普遍的现象，羚羊和狮子疲于奔波都是为了同样一个目标――生存。这也是动物的本能，而人生在世总要为着这样或那样的目标去劳作去追逐――有意识的活动和单纯的本能是人与动物的重要区别。动物尚且能为生存而奔跑，何况人乎？人生活在世上干什么，就是要实现自己的生存价值，就是要让社会承认自己价值的存在，这才是有意义的生活。自暴自弃蝇营狗苟之徒，或许也能活得下来，但这绝不是生活！著名物理学家库珀说：”人生就像打高尔夫球时击出去的球，要有一个明确的目标。“是的，人的生活不能没有个明确的目标。没有目标，就没有前进的方向和动力，就象大海中的船舶，没有舵手，它就随时可能触礁或搁浅；没有目标，人就活得消极被动，庸庸碌碌无所作为。譬如，庙里撞钟的和尚，他从来不会去考虑自己哪天会失业，或者哪天去跳槽，只是苦度年华，静待归西的涅磐。如果羚羊和狮子都蛰伏不起，它们也难免要被吃掉或饿死；但它们总是一跃而起向前奔去，这是因为它们有生存的目标，这既是方向，也是动力。有了心中的目标，就要去追逐它，去为它潜心地付出，哪怕是为伊憔悴衣带渐宽？是真正的生活，就要有百折不挠的激情，就要去做自己力所能及的事，就要有中流击水的魄力和不到长城非好汉的气概！",
 * "timestamp": "2019-05-18 16:51:55"
 */
public class NoteModel {
    public String id;
    public String title;
    public String content;
    @SerializedName("timestamp")
    public String timeStamp;
}
