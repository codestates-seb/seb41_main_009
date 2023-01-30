import styled from 'styled-components';
import { LabelMedium } from '../../styles/typo';
import { TextButton } from '../atoms/Buttons';

const Container = styled.div`
  width: 100%;
  height: fit-content;
  background-color: var(--gray-700);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 50px;
  margin-top: 500px;
  z-index: 9999;
`;

const Body = styled.div`
  display: flex;
  width: 1440px;
  justify-content: space-between;
  align-items: center;
`;

const Logo = styled.div`
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 700;
  font-size: 36px;
  line-height: 52px;
  /* identical to box height, or 144% */

  /* primary/primaryB2 */

  color: #efefef;
`;
const RowList = styled.div`
  width: 100%;

  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 0px;
`;

const RowListFitContent = styled.div`
  display: flex;
  flex-direction: row;
  width: fit-content;
  align-items: center;
  justify-content: space-between;
`;

const ColumnList = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: flex-end;
  justify-content: space-between;
`;

const UserButton = styled(TextButton)`
  width: 70px;
  justify-content: center;
  color: var(--gray-100);

  &:visited {
    color: var(--gray-200);
  }

  ${LabelMedium}
`;

const Footer = () => {
  return (
    <Container>
      <Body>
        <ColumnList>
          <RowList>
            <Logo>IntoRest</Logo>
            <ColumnList>
              <UserButton>Move To</UserButton>
              <RowListFitContent>
                <UserButton to="/">login</UserButton>
                <UserButton to="/">signup</UserButton>
                <UserButton to="/">showcase</UserButton>
                <UserButton to="/">posts</UserButton>
                <UserButton to="/">series</UserButton>
                <UserButton to="/">users</UserButton>
                <UserButton to="/">search</UserButton>
              </RowListFitContent>
            </ColumnList>
          </RowList>
        </ColumnList>
      </Body>
    </Container>
  );
};

export default Footer;
