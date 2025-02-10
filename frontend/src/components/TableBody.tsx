import { ItemObject } from "./ItemObject";

export const TableBody = ({ itemArray }: { itemArray: ItemObject[] }) => {
  const itemHtml = itemArray.map((elem, index) => {
    let row: number = index;
    let day: string;
    let month: string;
    let year: string;
    if (new Date(elem.expiryDateddmmyyyy).getDate() < 10) {
      day = "0" + new Date(elem.expiryDateddmmyyyy).getDate().toString();
    } else {
      day = new Date(elem.expiryDateddmmyyyy).getDate().toString();
    }
    if (new Date(elem.expiryDateddmmyyyy).getMonth() + 1 < 10) {
      month =
        "0" + (new Date(elem.expiryDateddmmyyyy).getMonth() + 1).toString();
    } else {
      month = (new Date(elem.expiryDateddmmyyyy).getMonth() + 1).toString();
    }
    year = new Date(elem.expiryDateddmmyyyy).getFullYear().toString();
    let stringDate: string = day + "/" + month + "/" + year;

    return (
      <tr key={row}>
        <td className="numericalCell">{row}</td>
        <td className="stringCell">{elem.itemName}</td>
        <td className="numericalCell">{stringDate}</td>
        <td className="numericalCell">{elem.quantity}</td>
        <td className="numericalCell">
          {(elem.priceInPence / 100).toFixed(2)}
        </td>
      </tr>
    );
  });
  return <tbody>{itemHtml}</tbody>;
};
