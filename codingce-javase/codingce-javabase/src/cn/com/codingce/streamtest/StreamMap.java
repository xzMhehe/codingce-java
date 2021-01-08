package cn.com.codingce.streamtest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream作为Java 8的一大特点，是对集合对象功能的增强，***.stream().map(...).collect(Collectors.toList())中，***需要是一个List类型的对象.
 * @Author: Jiangjun
 * @Date: 2019/10/21 9:08
 */
public class StreamMap {

    public void Test1(List<Person> person){
        List<PersonDTO> personDto = person.stream().map(StreamMap::convertPersonToPersonDto).collect(Collectors.toList());
        List<Person> personAfter = personDto.stream().map(StreamMap::convertPersonDtoToPerson).collect(Collectors.toList());
    }

    public static Person convertPersonDtoToPerson(PersonDTO personDTO){
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setAge(personDTO.getAge());
        person.setGender(personDTO.getGender());
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < personDTO.getHobby().size(); i++){
            sb.append(personDTO.getHobby().get(i)+",");
        }
        sb.append("]");
        person.setHobby(sb.toString());
        return person;
    }

    public static PersonDTO convertPersonToPersonDto(Person person){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(person.getName());
        personDTO.setAge(person.getAge());
        personDTO.setGender(person.getGender());
        List<String> hobby = new ArrayList<>();
        String[] temp = person.getHobby().split(",");
        for(int i = 0; i < temp.length; i++){
            hobby.add(temp[i]);
        }
        personDTO.setHobby(hobby);
        return personDTO;
    }


}
