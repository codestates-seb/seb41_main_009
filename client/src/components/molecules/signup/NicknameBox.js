import { INVALID_NICKNAME } from '../../../constants/Messages';
import { Box, Label, SignupInput } from '../../atoms/signup/SignupComponents';
import { isValidNickname } from '../../../functions/isValid';

const NicknameBox = ({ setNickname, nicknameMessage, setNicknameMessage }) => {
  const onNicknameInput = e => {
    const nicknameValue = e.target.value;
    setNickname(nicknameValue);

    if (isValidNickname(nicknameValue) || nicknameValue.length === 0) {
      setNicknameMessage('');
    } else {
      setNicknameMessage(INVALID_NICKNAME);
    }
  };

  return (
    <Box>
      <Label>Nickname</Label>
      <SignupInput placeholder="Enter Your Nickname" onChange={onNicknameInput} message={nicknameMessage} />
    </Box>
  );
};

export default NicknameBox;
