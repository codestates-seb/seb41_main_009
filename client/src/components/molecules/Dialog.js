import styled from 'styled-components';
import { BlackButton, WhiteButton } from '../atoms/Buttons';
import { LabelListTitle } from '../../styles/typo';

const Container = styled.div`
  box-sizing: border-box;

  /* Auto layout */

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 20px;
  gap: 20px;
  width: ${props => (props.width ? props.width : '447px')};
  height: ${props => (props.height ? props.height : '278px')};

  background: ${props => props.background || '#ffffff'};
  border: 1px solid #000000;
  box-shadow: 8px 8px 0px #000000;
  overflow: hidden;
`;

const MessageArea = styled.div`
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  padding: 10px;
  gap: 10px;

  width: 402px;
  height: 148px;
  ${LabelListTitle}
`;

const ButtonArea = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: flex-start;
  padding: 0px;
  gap: 20px;
`;

const Dialog = ({ message, background }) => {
  return (
    <Container background={background}>
      <MessageArea>{message || 'Dialog Message'}</MessageArea>
      <ButtonArea>
        <WhiteButton width="fit-content">Cancel</WhiteButton>
        <BlackButton width="fit-content"> OK</BlackButton>
      </ButtonArea>
    </Container>
  );
};

export default Dialog;
