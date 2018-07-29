package leetcode

object Main extends App {
  val result = TwoSum.twoSum(Array(3,2,4), 6)
  println(result.deep.mkString("\n"))
}

object TwoSum {
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    nums.zipWithIndex.foldLeft(Map.empty[Int, Int])((map, tuple) => {
      if (map.get(target - tuple._1).isEmpty) {
        map + (tuple._1 -> tuple._2)
      } else {
        return Array(map.getOrElse(target - tuple._1, -1), tuple._2)
      }
    })
    null
  }


  def twoSum1(nums: Array[Int], target: Int): Array[Int] = {
    uniquePairs(nums.zipWithIndex)
      .find(arr => sum(arr) == target)
      .map { case (first, second) => Array(first._2, second._2) }
      .getOrElse(Array())
  }

  private def uniquePairs(nums: Array[(Int, Int)]) = {
    for {
      (x, idxX) <- nums.zipWithIndex
      (y, idxY) <- nums.zipWithIndex
      if idxX < idxY
    } yield (x, y)
  }

  private def sum(tuple: ((Int, Int),(Int, Int))):Int = {
    tuple._1._1 + tuple._2._1
  }
}

object AddTwoNumbers {
//  You are given two non-empty linked lists representing two non-negative integers.
//  The digits are stored in reverse order and each of their nodes contain a single digit.
//  Add the two numbers and return it as a linked list.
//
//  You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
//  Example
//
//  Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//  Output: 7 -> 0 -> 8
//  Explanation: 342 + 465 = 807.

  class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x
  }

  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    (l1, l2) match {
      case (null, null) => null
      case _ => {
        val (l1Next, l2Next, sum) = (l1, l2) match {
          case (listNode1, null) => (listNode1.next, null, listNode1.x + 0)
          case (null, listNode2) => (null, listNode2.next, listNode2.x + 0)
          case _ => (l1.next, l2.next, l1.x + l2.x)
        }

        val node = new ListNode(sum % 10)

        var newL1Next = l1Next
        if (sum > 9) {
          val l1NextValue = ( if (l1Next == null) 0 else l1Next.x ) + 1
          newL1Next = new ListNode(l1NextValue)
          newL1Next.next = if(l1Next == null) null else l1Next.next
        }

        node.next = addTwoNumbers(newL1Next,l2Next)
        node
      }
    }
  }
}

object LongestSubstringWithoutRepeatingCharacters {
//  Given a string, find the length of the longest substring without repeating characters.
//
//  Examples:
//
//  Given "abcabcbb", the answer is "abc", which the length is 3.
//  Given "bbbbb", the answer is "b", with the length of 1.
//  Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

  def lengthOfLongestSubstringRecur(s: String): Int = {
    if (s.isEmpty) 0
    val resultMap = s.zipWithIndex.foldLeft(Map[String, Int]("max" -> 0, "last" -> -1))((map, pair) => {
      if (map.getOrElse("" + pair._1, -1) > map.getOrElse("last", -1))
        map + ("last" -> map.getOrElse("" + pair._1, -1), ("" + pair._1) -> pair._2)
      else {
        map + ("" + pair._1 -> pair._2, "max" -> Math.max(map.getOrElse("max", 0), pair._2 - map.getOrElse("last", -1)))
      }
    })
    resultMap.getOrElse("max", 0)
  }

  def lengthOfLongestSubstring(s: String): Int = {
    val dict = collection.mutable.Map[Char, Int]()
    var maxLength = 0
    var pointer = 0

    s.zipWithIndex.foreach {
      case (value, index) => {
        if (dict.contains(value)) {
          pointer = Math.max(dict.getOrElse(value, 0) + 1, pointer)
        }
        maxLength = Math.max(index - pointer + 1, maxLength)
        dict(value) = index
      }
    }
    maxLength
  }
}




