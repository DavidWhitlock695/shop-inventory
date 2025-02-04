import { ItemObject } from "./ItemObject";

const convertDate = (utcInMiliseconds: number) => {
  return new Date(utcInMiliseconds).toDateString;
};

export const TableBody = ({ itemArray }: { itemArray: ItemObject[] }) => {
  const itemHtml = itemArray.map((elem) => {
    return (
      <tr key={elem.id}>
        <td>{elem.id}</td>
        <td>{elem.itemName}</td>
        <td>{elem.expiryDateddmmyyyy}</td>
        <td>{elem.quantity}</td>
        <td>{elem.priceInPence}</td>
      </tr>
    );
  });
  return <tbody>{itemHtml}</tbody>;
};
