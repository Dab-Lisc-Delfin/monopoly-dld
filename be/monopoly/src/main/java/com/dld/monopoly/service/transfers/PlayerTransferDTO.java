//package com.dld.monopoly.service.transfers;
//
//import com.dld.monopoly.model.Player;
//import com.dld.monopoly.model.dto.PlayerDTO;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class PlayerTransferDTO {
//
//    private PropertiesTransferDTO propertiesTransferDTO;
//
//    public PlayerDTO responseDTO(Player player) {
//        PlayerDTO playerDTO = new PlayerDTO;
//        playerDTO.setId(player.getId());
//        playerDTO.setNickname(playerDTO.getNickname());
//        playerDTO.setDices(playerDTO.getDices());
//        playerDTO.setPlayerIndex(player.getPlayerIndex());
//        playerDTO.setAfterRoll(player.isAfterRoll());
//        playerDTO.setMoney(playerDTO.getMoney());
//        playerDTO.setPosition(player.getPosition());
//        playerDTO.setInJail(playerDTO.isInJail());
//        playerDTO.setDoubletCounter(player.getDoubletCounter());
//        playerDTO.setAllDiceRollsInThisTour(player.getAllDiceRollsInThisTour());
//        playerDTO.setPropertiesDTO(propertiesTransferDTO);
//    }
//
//    //record PlayerDTO(
//    //        int id,
//    //        String nickname,
//    //        int[] dices,
//    //        int money,
//    //        int playerIndex,
//    //        int allDiceRollsInThisTour,
//    //        int doubletCounter,
//    //        Field position,
//    //        boolean isInJail,
//    //        List<RentableProperty> properties,
//    //        boolean afterRoll,
//    //) {
//}
